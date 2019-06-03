import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../services/login.service";
import {FormControl, FormGroupDirective, NgForm, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = new FormControl('',[
    Validators.required
  ]);

  password = new FormControl('',[
    Validators.required
  ]);
  constructor(private loginservice:LoginService) { }

  ngOnInit() {
    this.loginservice.islogedin();
  }

  login(){
    var formContent = new FormData();
    formContent.append('username',this.username.value);
    formContent.append('password',this.password.value);
    this.loginservice.login(formContent);
  }


}
