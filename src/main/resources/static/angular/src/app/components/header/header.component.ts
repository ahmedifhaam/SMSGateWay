import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../../services/login.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DatastoreService} from "../../services/datastore/datastore.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  showHeaderControls:boolean=false;


  constructor(private loginService:LoginService,private router:Router,public datastore:DatastoreService) { }

  ngOnInit() {

    this.datastore.subscribeUpdate().subscribe(isit=>{
      this.showHeaderControls = isit;
      console.log("subscription");
    })
  }



  logout(){
    this.loginService.logout();
  }

  adminpanel(){
    if(this.router.url === "/adminpanel") {
      this.router.navigateByUrl("/processing")

    }else{
      this.router.navigateByUrl("adminpanel");
    }
  }

  gotoPasswordReset(){
    this.router.navigateByUrl("password_reset");
  }

  gotoMain(){
    this.router.navigateByUrl("/processing");
  }

  gotoAdmin(){
    this.router.navigateByUrl("adminpanel");
  }
}
