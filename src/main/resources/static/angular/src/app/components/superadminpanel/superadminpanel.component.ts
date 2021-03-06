import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {MaskserviceService} from "../../services/mask/maskservice.service";
import {stringify} from "querystring";
import {Role, User} from "../../models/user";
import {UserService} from "../../services/user/user.service";
import {forEach} from "@angular/router/src/utils/collection";
import {DatastoreService} from "../../services/datastore/datastore.service";
import {RegexentryService} from "../../services/regexentry/regexentry.service";
import {logging} from "selenium-webdriver";
import Entry = logging.Entry;
import {RegexEntry} from "../../models/RegexEntry";

@Component({
  selector: 'app-superadminpanel',
  templateUrl: './superadminpanel.component.html',
  styleUrls: ['./superadminpanel.component.css']
})
export class SuperadminpanelComponent implements OnInit {

  currentlySelectedUser:User;

  maskvalue = new FormControl('',[
    Validators.required
  ]);


  regname = new FormControl('',[
    Validators.required
  ]);

  regex = new FormControl('',[
    Validators.required
  ]);


  username = new FormControl('',[
    Validators.required
  ]);

  password = new FormControl('',[
    Validators.required
  ]);
  passwordconfirm = new FormControl('',[
    Validators.required
  ]);

  name = new FormControl('',[
    Validators.required
  ]);

  adminrole:boolean = false;
  sadminrole:boolean = false;

  users:User[] = [];
  entries:RegexEntry[] = [];
  private currentlySelectedEntry: RegexEntry;

  constructor(private maskservice:MaskserviceService,private userservice:UserService
              ,private datastore:DatastoreService,private regeexservice:RegexentryService) { }

  ngOnInit() {
    this.getAllUsers();

  }

  public addMask(){
    if(this.maskvalue.value==""){
      alert("Can't add empty mask");
    }else{
      this.maskservice.addmask(this.maskvalue.value).subscribe(value => {
        if(value==null){
          alert("Failed to add (Mask Already Exist)");
        }else{
          alert("Successfully added the mask");
          this.maskvalue.setValue("");
        }
      },error1 => {
        alert(stringify(error1));
      });
    }


  }

  public setcurrentregexentry(entry:RegexEntry){
    this.currentlySelectedEntry = entry;
    this.updateEntryDetails();
  }

  public setcurrentuser(user:User){
    this.currentlySelectedUser = user;
    this.updateUserDetails()
  }
  private updateEntryDetails() {
    this.regname.setValue(this.currentlySelectedEntry.name);
    this.regex.setValue(this.currentlySelectedEntry.expression);
  }

  updateUserDetails(){
    this.adminrole = false;
    this.sadminrole = false;
    // this.password.setValue(this.currentlySelectedUser.password);
    this.username.setValue(this.currentlySelectedUser.username)
    this.name.setValue(this.currentlySelectedUser.name);
    this.currentlySelectedUser.roles.forEach(role=>{
      switch (role.role) {
        case "ADMIN":
          this.adminrole = true;
          break;

        case "SADMIN":
          this.sadminrole = true;
          break;
      }
    })
  }

  getAllUsers(){
    this.userservice.getallusers().subscribe((users:User[])=>{
      this.users.splice(0,this.users.length);
      users.forEach(user=>{
        this.users.push(user);
      });
    });

  }

  getAllRegexEntries(){
    this.regeexservice.getAllEntries().subscribe((entries:RegexEntry[])=>{
      this.entries.splice(0,this.entries.length);
      entries.forEach(entry=>{
        this.entries.push(entry);
      });
    });
  }

  getRegexEntryObject(){
    var entry:RegexEntry={
      name : this.regname.value,
      expression:this.regex.value
    }

    return entry;
  }


  updateEntry(){
    this.regeexservice.updateEntry(this.getRegexEntryObject()).subscribe(response=>{
      if(response['response']=="SUCCESSFUL") alert("Successfully updated");
    },error1 => alert(stringify(error1)));
  }

  insertEntry(){
    this.regeexservice.insertEntry(this.getRegexEntryObject()).subscribe(response=>{
      if(response['response']=="SUCCESSFUL") alert("Successfully Added");
    },error1 => alert(stringify(error1)));
  }

  deleteEntry(){
    this.regeexservice.deleteEntry(this.getRegexEntryObject()).subscribe(response=>{
      if(response['response']=="SUCCESSFUL") alert("Successfully Deleted");
    },error1 => alert(stringify(error1)));
  }

  getuserObject(){

    if(this.password.value!=this.passwordconfirm.value) {
      alert("Passwords do not match");
      return null;
    }
    var user:User = {
      username:this.username.value,
      password :this.password.value,
      name : this.name.value,
      roles:[]
    };

    var adminrole:Role = {role:"ADMIN"};
    // role.role = "ADMIN";
    if(this.adminrole)user.roles.push(adminrole);

    var sadminrole = {role:"SADMIN"};
    if(this.sadminrole)user.roles.push(sadminrole);

    return user;
  }

  updateuser(){

    var user:User = this.getuserObject();
    if(user!=null){
      this.userservice.updateuser(user).subscribe(response=>{
        if(response['response']=="SUCCESSFUL") alert("Successfully updated");
      },error1 => alert(stringify(error1)));
    }

    this.getAllUsers();
  }

  addnewuser(){
    var user:User = this.getuserObject();
    if(user!=null) {
      this.userservice.addnewuser(user).subscribe(response => {
        if (response['response']== "SUCCESSFUL") alert("Successfully added");
      }, error1 => alert(stringify(error1)));
    }

    this.getAllUsers();

  }

  deleteuser(){
    var user:User = this.getuserObject();
    if(user!=null){
      this.userservice.deleteuser(user).subscribe(response=>{
        if (response['response']== "SUCCESSFUL") alert("Successfully deleted the user");
      }, error1 => alert(stringify(error1)));
    }

    this.getAllUsers();
  }

  clearselection(){
    this.regex.reset()
    this.regname.reset()
    this.username.reset()
    this.password.reset()
    this.passwordconfirm.reset()
    this.sadminrole = false;
    this.adminrole = false;
    this.name.reset()

  }


}
