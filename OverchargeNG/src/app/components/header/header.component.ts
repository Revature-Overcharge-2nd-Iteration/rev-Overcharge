import { Component, EventEmitter, OnInit, Output} from '@angular/core';
import { ObjectivesService } from 'src/app/services/objectives.service';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  
  @Output() toggleSidebarForMe: EventEmitter<any> = new EventEmitter();

  value: number;
  progressBar: string;
  dailyObj = this.objData.loginObj;
  nameObj = this.dailyObj.name;
  pointsObj = this.dailyObj.pointsToAward;

  nameObj2 = this.objData.dailyObj2.name;
  pointsObj2 = this.objData.dailyObj2.pointsToAward;

  nameObj3 = this.objData.dailyObj3.name;
  pointsObj3 = this.objData.dailyObj3.pointsToAward;

  nameObj4 = this.objData.weeklyObj.name;
  pointsObj4 = this.objData.weeklyObj.pointsToAward;

  nameObj5 = this.objData.weeklyObj2.name;
  pointsObj5 = this.objData.weeklyObj2.pointsToAward;

  nameObj6 = this.objData.weeklyObj3.name;
  pointsObj6 = this.objData.weeklyObj3.pointsToAward;

  responseMessage: string = '';
  loggedIn : boolean;
  loginPoints: boolean;
  closeResult = '';
  username: string = '';
  password: string = '';
  user: any;
  sw1: boolean = false;
  sw2: boolean = false;
  sw3: boolean = false;
  modalRef: any;

  constructor(private loginServ: LoginService, private router: Router, private modalService: NgbModal, private objData: ObjectivesService) { }

  ngOnInit(): void { 
    this.updateValue();

    if (this.value == 100){
      this.progressBar = "progress-bar";
    }
  }

  toggleSidebar(): void {
    this.toggleSidebarForMe.emit();
  }

  getUsername(): string {
    this.loggedIn = this.loginServ.loggedIn;
    return this.loginServ.getUsername();
  }

  logout(): void {
    this.loggedIn = !this.loggedIn;
    this.loginServ.setUsername('Guest');
    this.responseMessage = "Logging out";
  }
  
  updateValue(){
 
    this.value = this.objData.getValue();
    this.progressBar = this.objData.getProgressBar();
  }

  


  open(content:any) {

    this.modalRef = this.modalService.open(content,
  {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
    this.closeResult =
      `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
    return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
    return 'by clicking on a backdrop';
    } else {
    return `with: ${reason}`;
    }
  }

  login() {
    console.log(this.username);
    console.log(this.password);
    let loginAttempt = new User(0,this.username, this.password,0,0);
    this.loginServ.login(loginAttempt).subscribe(
      (response) => {
        if (response) {
          this.user = response;
          console.log(this.user);

          if(this.user.objectives.length != 0){
            this.loginPoints = true;
          }

          if (this.user.username == "user"){
            this.loginPoints = true;
            console.log(this.loginPoints) 
          }

          this.loginServ.setUsername(this.user.username);
          console.log("logged in: ", this.user.username);
          this.sw1 = false;
          this.sw2 = true;
          this.sw3 = false;
          
          window.setTimeout(() => {
            this.modalService.dismissAll();
            this.sw1 = false;
            this.sw2 = false;
            this.sw3 = false;
           }, 1200);
        } else {
          console.log("Invalid login...");
          this.sw1 = true;
          this.sw2 = false;
          this.sw3 = false;
        }
      },
      (error) => {
        console.log("Login Error...");
        this.sw3 = true;
        this.sw1 = false;
        this.sw2 = false;
      })
}
}
