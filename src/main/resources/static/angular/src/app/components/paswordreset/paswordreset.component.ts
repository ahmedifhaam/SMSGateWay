import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {FormControl, Validators} from "@angular/forms";
import {stringify} from "querystring";
import {MaskserviceService} from "../../services/mask/maskservice.service";
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-paswordreset',
  templateUrl: './paswordreset.component.html',
  styleUrls: ['./paswordreset.component.css']
})
export class PaswordresetComponent implements OnInit {

  password = new FormControl("",[
    Validators.required
  ],);
  passwordconfirm = new FormControl('',[
    Validators.required
  ]);

  constructor(private userservice:UserService) { }

  ngOnInit() {
  }

  public clearinputs(){
    this.password.reset()
    this.passwordconfirm.reset();
  }

  getuserObject(){

    if(this.password.value!=this.passwordconfirm.value) {
      alert("Passwords do not match");
      return null;
    }
    var user:User = {
      username:'',
      password :this.password.value,
      name : this.password.value,
      roles:[]
    };

    return user;
  }

  public resetpassword(){
    var user = this.getuserObject();
    if(user!=null) this.userservice.resetuserpassword(user).subscribe(response=>{
      if(response['response']=='SUCCESSFUL'){
        alert("Updated successfully");
      }else{
        alert("Failed to update");
      }
    },error1 => {
      alert(stringify(error1))
    })
  }




}
