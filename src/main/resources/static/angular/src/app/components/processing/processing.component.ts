import {Component, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {FormControl, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {ErrorStateMatcher, MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {FileUploader} from "ng2-file-upload";
import {SendsmsserviceService} from "../../services/sendsms/sendsmsservice.service";
import {Observable,of} from "rxjs";
import {debounceTime, distinctUntilChanged, map, startWith} from "rxjs/operators";
import {MaskserviceService} from "../../services/mask/maskservice.service";
import {Role, User} from "../../models/user";
import {UserService} from "../../services/user/user.service";
import {stringify} from "querystring";
import {Messagelog} from "../../models/messagelog";


export interface Mask {
  id:number,
  value:string
}

@Component({
  selector: 'app-processing',
  templateUrl: './processing.component.html',
  styleUrls: ['./processing.component.css']
})
export class ProcessingComponent implements OnInit,OnChanges {
  private samplecsvUrl: string = "/sms/api/samplecsv";
  ngOnChanges(changes: SimpleChanges): void {

  }

  displayreport = true;

  displayedColumns: string[] = ['user', 'date', 'message','mask','number', 'status'];
  results:Messagelog[] = [];
  datasource = new MatTableDataSource<Messagelog>(this.results);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  uploadFileURL = 'api/sendmultisms';
  uploader = new FileUploader({ url : this.uploadFileURL, itemAlias: 'file'})
  message = new FormControl('',[
    Validators.required,
  ]);
  phonenumber = new FormControl('',[
    Validators.required,
  ]);
  maskcontrol = new FormControl('',[
    Validators.required,
  ]);

  matcher = new MyErrorStateMatcher();



  options: Mask[]= [
    // {
    //   "value": "U Kelaniya",
    //   "id": 1
    // },
    // {
    //   "value": "UoK Pay",
    //   "id": 2
    // },
    // {
    //   "value": "UoK R",
    //   "id": 3
    // },
    // {
    //   "value": "UoK P",
    //   "id": 4
    // },
    // {
    //   "value": "UoK Admin",
    //   "id": 5
    // },
    // {
    //   "value": "UoK AcEst",
    //   "id": 6
    // },
    // {
    //   "value": "UoK Exam",
    //   "id": 7
    // }
  ];

  filteroptions :Observable<Mask[]>;



  constructor(private smsservice:SendsmsserviceService,private maskservice:MaskserviceService) {

  }




  ngOnInit() {
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

    this.maskservice.getallmasks().subscribe((masks:Mask[])=>{
      this.options = masks;
      this.filteroptions = of(this.options.slice());
      // alert("Downloaded");
    });


    this.uploader.onBuildItemForm = (fileitem:any,form:any) =>{
      form.append('message',this.message.value)
      form.append('mask',this.maskcontrol.value['value'])
    };
    this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
      console.log('ImageUpload:uploaded:', item, status, response);
      response = JSON.parse(response);
      this.results = response;
      // this.fileuploadedName = response['fileName']
      // this.filesize = response['size']
      // this.fileType = response['fileType']
      // this.dpupdatesuccess = response['dbupdatedsuccessfully']
      // alert('File uploaded successfully');
      // this.refreshEntries("");
      this.datasource.data = this.results;
      console.log(response);
      alert("Requirement Processed");
    };



    this.filteroptions = this.maskcontrol.valueChanges
      .pipe(
        startWith<string | Mask>(''),
        map(mask =>typeof mask ==='string' ? mask: mask.value),
        map(name=>name?this._filter(name):this.options.slice())
      );
  }

  displayFn(mask?:Mask):string | undefined{

    return mask ? mask.value  : undefined;
  }

  _filter(name:string):Mask[]{
    console.log("name value "+ name);
    const filterValue = name.toLowerCase();
    return this.options.filter(option=>
      option.value.toLowerCase().indexOf(filterValue) === 0
    );
  }

  public sendsms(){
    this.smsservice.sendsms(this.phonenumber.value,this.message.value,this.maskcontrol.value['value']).subscribe(
      response =>{
        console.log(response);
        if(response['message'].status=="SENT"){
          alert("Message to the number "+response['message'].phonenumber+" has been sent successfully")
        }else{
          alert("Message to the number "+response['message'].phonenumber+" has not been sent ("+response['message'].status)

        }

      }
    );
    // console.log("this,"+this.smsservice.sendsms(this.phonenumber.value,this.message.value));
  }

  public sendmultisms(){
    this.uploader.uploadAll();
  }





  applyFilter(filterValue: string) {
    this.datasource.filter = filterValue.trim().toLowerCase();


    if (this.datasource.paginator) {
      this.datasource.paginator.firstPage();
    }
  }
  
  downloadsamplefile(){
    window.open(this.samplecsvUrl);
  }

  resetformdata(){
    this.maskcontrol.reset('');
    // this.maskservice.getallmasks().subscribe((masks:Mask[])=>{
    //   this.options = masks;
    // });
    this.message.reset();
    this.phonenumber.reset();
  }
}

  

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
