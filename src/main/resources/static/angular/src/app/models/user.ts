

export interface  User {

  username:string;
  password: string ;
  name: number ;
  roles: Role[]   ;

}

export interface Role {
  role:string
}
