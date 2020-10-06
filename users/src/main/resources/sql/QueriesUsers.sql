getUsers<[
	SELECT 
		user_id,
		user_name,
		user_last_name,
		user_mother_last_name,
		user_rfc,
		user_birth_date
	FROM tbl_user
]>
getUserbyId<[
	SELECT 
		user_id,
		user_name,
		user_last_name,
		user_mother_last_name,
		user_rfc,
		user_birth_date
	FROM tbl_user
	WHERE
		user_id = ?
]>
insertUser<[
	INSERT INTO 
		tbl_user(
			user_name,
			user_last_name,
			user_mother_last_name,
			user_rfc,
			user_birth_date
		) VALUES (?, ?, ?, ?, ?)
]>
deleteUserbyId<[
	DELETE
	FROM tbl_user
	WHERE
		user_id = ?
]>

