import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ACCESS_TOKEN, baseUrl } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DashboardServiceService {

  constructor(private http:HttpClient) { }
  fetchJonProposals():Observable<any>{
    var headers=this.getHeaders();
    console.log(headers);
    return this.http.get(`${baseUrl}v1/jobs/getAllJobs`,{headers})
  }


  getHeaders():HttpHeaders{
    var userString=localStorage.getItem('user');

    if(userString==null){
      userString="{}";
    }
      var user=JSON.parse(userString);
      const headers= new HttpHeaders()
        .set('content-type', 'application/json')
        .set('Access-Control-Allow-Origin', '*')
      .set(`AccessToken`,user.accessToken);

      return headers;
  }

  udpateJobStatus(data: any):Observable<any>{
    var headers=this.getHeaders();
    console.log(headers)
    return this.http.post(`${baseUrl}v1/jobs/updateStatus`,data,{headers})
  }

}
