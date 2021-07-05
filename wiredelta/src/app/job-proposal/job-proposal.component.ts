import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DashboardServiceService } from '../dashboard-service.service';
import {ViewEncapsulation} from '@angular/core';


@Component({
  selector: 'app-job-proposal',
  templateUrl: './job-proposal.component.html',
  styleUrls: ['./job-proposal.component.css']
})
export class JobProposalComponent implements OnInit {

  jobData:any;

  constructor(@Optional() @Inject(MAT_DIALOG_DATA) public data: any,private service:DashboardServiceService
               ,public matDialogRef: MatDialogRef<JobProposalComponent>) { 
    this.jobData=data;
    console.log(this.jobData);
  }

  ngOnInit(): void {

  }

  acceptJobProposal(){
    this.updateJobProposal("ACCEPTED");

  }

  rejectJobProposal(){
    this.updateJobProposal("REJECTED");
  }

  updateJobProposal(status:String){

    var updateJobStatusRequest=new UpdateJobStatusRequest(this.jobData.id,status);
    
    this.service.udpateJobStatus(updateJobStatusRequest).subscribe(result=>{
      if(result.error==null){
        console.log("success")
        console.log(result);
        this.matDialogRef.close(true);
      }else{
        console.log(result);
        this.matDialogRef.close(false);
      }
    })
  }
}

class UpdateJobStatusRequest{
  id:String;
  status:String;
  constructor(id: String, status: String) {
    this.id = id;
    this.status = status;
   }
}