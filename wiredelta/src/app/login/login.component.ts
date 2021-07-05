import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from '../auth-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formGroup: FormGroup;
  signInError:String="";

  constructor(private authService:AuthServiceService,private router: Router) {
   }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(){
    this.formGroup=new FormGroup({
      userName:new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required])
    })
  }

  loginProcess(){
    if(this.formGroup.valid){
      this.authService.login(this.formGroup.value).subscribe(result=>{
        if(result.error==null){
          console.log("success")
          localStorage.setItem('user', JSON.stringify(result.data));
          this.router.navigate(['dashboard']);
        }else{
          console.log(result);
          this.signInError=result.error.errorMessage;
        }
      })
    }
  }
}
