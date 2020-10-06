import { Component, OnInit } from '@angular/core';
import { UserService } from './user.service';
import { ResponseDTO } from './DTO/ResponseDTO';
import { UsersDTO } from './DTO/UsersDTO';
import { FormControl } from '@angular/forms'; 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  title = 'user';
  users: UsersDTO[];
  resp: ResponseDTO;
  userSearch: UsersDTO;
  idSearchForm = new FormControl('');
  idSearch : string;


  headers = ["Nombre", "Apellido P", "Apellido M", "RFC", "Fecha Nacimiento"];

  constructor(private userService: UserService) {
  }

  ngOnInit() {
  }

  getAllUsers() {
      this.userService.getUsers().subscribe(value => { 
        this.resp = value;

        if(this.resp.status) {
            this.users = this.resp.object as UsersDTO[];
        }

      }
    );
  }

  clearAllUsers() {
    this.users = null;
  }

  getUserById(idSearch) {
    this.userService.getUserById(idSearch).subscribe(value => { 
        this.resp = value;
       
        if(this.resp.status) {
        
          this.userSearch = this.resp.object as UsersDTO;
          console.log(this.userSearch)
        }

        
      }
    );
  }

}
