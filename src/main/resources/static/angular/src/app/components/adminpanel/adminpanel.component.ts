import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {Messagelog} from "../../models/messagelog";
import {MessagelogserviceService} from "../../services/messagelog/messagelogservice.service";
import {User} from "../../models/user";
import {UserService} from "../../services/user/user.service";
import {Mask} from "../processing/processing.component";
import {MaskserviceService} from "../../services/mask/maskservice.service";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-adminpanel',
  templateUrl: './adminpanel.component.html',
  styleUrls: ['./adminpanel.component.css']
})
export class AdminpanelComponent implements OnInit,AfterViewInit {

  status = ""
  number = ""

  displayedColumns: string[] = ['user', 'date', 'message','mask','number', 'status'];
  data: Messagelog[] = [];
  datasource = new MatTableDataSource<Messagelog>(this.data);
  users:User[] =[];
  masks:Mask[] = [];

  maskControl = new FormControl('',[

  ]);

  userControl = new FormControl('',[]);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.updateData()
    this.loadfilterdata();
  }

  ngOnInit(){
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
    this.datasource.filterPredicate = (data:Messagelog,filterstring:string)=>{
      if(data.date.toString().toLowerCase().indexOf(filterstring)>-1)return true;
      if(data.user.toLowerCase().indexOf(filterstring)>-1)return true;
      if(data.message.message.toLowerCase().indexOf(filterstring)>-1)return true;
      if(data.message.status.toLowerCase().indexOf(filterstring)>-1)return true;
      if(data.message.mask.toLowerCase().indexOf(filterstring)>-1)return true;
      if(data.message.phonenumber.toString().toLowerCase().indexOf(filterstring)>-1)return true;
    };

  }

  constructor(private messagelogService:MessagelogserviceService,private userservice:UserService,private maskservice:MaskserviceService){

  }

  applyFilter(filterValue: string) {
    this.datasource.filter = filterValue.trim().toLowerCase();


    if (this.datasource.paginator) {
      this.datasource.paginator.firstPage();
    }
  }

  updateData(){
    this.messagelogService.getallmessagelog().subscribe((response:Messagelog[])=>{
      this.data = response;
      this.datasource.data = this.data;

    })

    this.userservice.getallusers().subscribe((users:User[])=>{
      this.users = users;
    })
  }

  loadfilterdata(){
    this.userservice.getallusers().subscribe((users:User[])=>{
      this.users = users;
    },error1 => {
      alert("Failed to load User data");
      console.log(error1);
    });

    this.maskservice.getallmasks().subscribe((masks:Mask[])=>{
      this.masks = masks;

    },error1 => {
      alert("Failed to load mask data");
      console.log(error1);
    })
  }

  buildqueryparamters(){
    var status = "status"+(this.status?"="+this.status:"");
    var mask = "mask"+(this.maskControl.value?"="+this.maskControl.value:"");
    var user = "user"+(this.userControl.value?"="+this.userControl.value:"");
    var number = "number"+(this.number?"="+this.number:"");

    var query = status+"&"+mask+"&"+user+"&"+number;
    return query;

  }

  downloadfile(){
    this.messagelogService.filemessagelogs(this.buildqueryparamters());
  }

  updatetable(){
    this.messagelogService.filteredmessagelogs(this.buildqueryparamters()).subscribe((response:Messagelog[])=>{
      this.data = response;
      this.datasource.data = this.data;

    })
  }

  resetcontrols(){
    this.status = "";
    this.userControl.reset();
    this.maskControl.reset();
    this.number = "";
  }
}
