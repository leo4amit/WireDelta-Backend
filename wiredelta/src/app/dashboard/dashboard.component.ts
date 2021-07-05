import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { DashboardServiceService } from '../dashboard-service.service';
import { JobProposalComponent } from '../job-proposal/job-proposal.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  opened: boolean=false;
  dataSource = new MatTableDataSource<JobsProposal>(ELEMENT_DATA);
  jobsProposals=[];
  result:any;
  isExpanded = true;
  isShowing = false;
  currentTabSelected=0;


  displayedColumns: string[] = ['UserName', 'Boat Type', 'Service', 'Boat Location','Job Type','Details'];
  
  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private dashBoardService:DashboardServiceService,
               private changeDetectorRefs: ChangeDetectorRef,
               private dialog:MatDialog,
               private router: Router) { }
    ngOnInit(): void {
      this.fetchJobProposals();
  }

  mouseenter() {
    if (!this.isExpanded) {
      this.isShowing = true;
    }
  }

  mouseleave() {
    if (!this.isExpanded) {
      this.isShowing = false;
    }
  }
 
 fetchJobProposals(){
    this.dashBoardService.fetchJonProposals().subscribe(result=>{
      if(result.error==null){
        console.log("success")
        console.log(result);
        this.result=result.data;
        this.selectJobStatusAndParse();
      }else{
        var error=result.error;
        if(error.errorCode=='WD_ERR_1003' || 
            error.errorCode == 'WD_ERR_1004' ||
            error.errorCode == 'WD_ERR_1005'){
          this.router.navigate(['error']);
        }
        console.log(result);
      }
    })
  }


  parseAsPerJobType(jobStatus:String){

   var jobProposals=this.result.jobProposals;
   var position=0;
   for (let i = 0; i < jobProposals.length; i++) {
        var jobProposal=jobProposals[i]; 
        var job=jobProposal.jobDto;
        if(jobProposal.status==jobStatus){

          var jobType="Normal"
          if(job.isEmergency){
            jobType="Emergency";
          }

        ELEMENT_DATA.push({userName:job.userName,
          boatType:job.boatType,
          service:job.service,
          boatLocation:job.boatLocation,
          jobType:jobType,
          position:position
        });
       }
       position++;
    }

    this.paginator.length=ELEMENT_DATA.length;
    this.dataSource=new MatTableDataSource<JobsProposal>(ELEMENT_DATA)
    this.changeDetectorRefs.detectChanges
    this.dataSource.paginator = this.paginator;
  }

  onTabSelected(event:any){
    this.currentTabSelected=event.index;
    this.selectJobStatusAndParse()
  }

  selectJobStatusAndParse(){

    ELEMENT_DATA=[]
    this.paginator.length=ELEMENT_DATA.length;
    this.dataSource=new MatTableDataSource<JobsProposal>(ELEMENT_DATA)
    this.changeDetectorRefs.detectChanges
    this.dataSource.paginator = this.paginator;
    
    if(this.currentTabSelected==0){
      this.parseAsPerJobType("PENDING")
    }else if(this.currentTabSelected==1){
      this.parseAsPerJobType("ACCEPTED")
    }else if(this.currentTabSelected==2){
      this.parseAsPerJobType("COMPLETED")
    }else if(this.currentTabSelected==3){
      this.parseAsPerJobType("REJECTED")
    }else if(this.currentTabSelected==4){
      this.parseAsPerJobType("CANCELLED")
    }

  }

  viewRecord(element:number){

    var jobProposal=this.result.jobProposals[element];

    const dialogRef = this.dialog.open(JobProposalComponent, {
      width: '500px',
      height:'550px',
      data: jobProposal,
      panelClass: 'custom-dialog-container'
    });

    dialogRef.afterClosed().subscribe(result => {

      if(result!=null){
        if(result){
          this.fetchJobProposals();
        }
      }
        console.log('The dialog was closed');
    });
  }
}

export interface JobsProposal {
  userName: string;
  boatType: String;
  service: String;
  boatLocation: String;
  jobType:String,
  position:number
}


let ELEMENT_DATA: JobsProposal[] = [ ];