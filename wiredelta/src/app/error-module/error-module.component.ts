import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-error-module',
  templateUrl: './error-module.component.html',
  styleUrls: ['./error-module.component.css']
})
export class ErrorModuleComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

 goBackToLogin(){
  this.router.navigate(['']);
}

}
