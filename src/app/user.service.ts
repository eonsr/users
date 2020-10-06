import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

  const HEADER = {
    headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Access-Control-Allow-Methods': 'GET',
        'Access-Control-Allow-Headers': 'Content-Type, Authorization'
    }),
  };

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(protected http: HttpClient) { }

  idtoSearch : string;

  idSearch : string;

  url : string;

  getUsers() : Observable<any> {
    return this.http.get('http://localhost:8080/api/v1/user/getUsers');
  }

  getUserById(idSearch): Observable<any> {

    console.log(idSearch);

    this.idtoSearch = "1";

    this.url = 'http://localhost:8080/api/v1/user/getUserById?id=' + this.idtoSearch;

    return this.http.get(this.url, HEADER);
  }

}
