import { Component, Injectable, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'modal-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
@Injectable()
export class LoginComponent implements OnInit {
  @ViewChild('loginModal') private modalContent: TemplateRef<LoginComponent>;
  private modalRef: NgbModalRef
  
  username: string = '';
  password: string = '';
  responseMessage: string = '';
  responseAttempted: boolean = false;
  loginPoints: boolean = false;
  role: number;

  showErrorMessage: boolean = false;
  errorMessage: string = '';

  constructor(private loginServ: LoginService, private modalServ: NgbModal, private router: Router) { }

  ngOnInit(): void {

  }

  open(){
    this.modalRef = this.modalServ.open(this.modalContent);
    this.modalRef.result.then();
  }

  close() {
    this.modalRef.close();
  }

  dismiss() {
    this.modalRef.dismiss();
  }

  login() {

    this.loginServ.login(this.username, this.password).subscribe((data: User) =>{
      this.router.navigateByUrl("/library");
    },  
    (err: HttpErrorResponse) => {
      console.log(err);
      this.showErrorMessage = true;
      this.errorMessage = err.error.message

    });
  }

  setResponseMessage(input: string) {
    this.responseAttempted = true;
    switch (input) {
      case "success":
        this.responseMessage = "Success! Logging in...";
        window.setTimeout(() => {
          this.close();
        }, 2000);
        break;
      case "fail":
        this.responseMessage = "Incorrect credentials";
        break;
      case "error":
        this.responseMessage = "Login Error...";
        break;
    }
  }
}
