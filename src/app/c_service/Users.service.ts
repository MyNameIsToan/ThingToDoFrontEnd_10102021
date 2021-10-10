import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Users } from '../common/Users';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  url = 'http://localhost:8081/User';
  SendToken !:string;
  constructor(private HttpClient : HttpClient) {

  }


  GetAll(){
    return  this.HttpClient.get(this.url);
  }

  SaveUser(users : Users){
    return this.HttpClient.post('http://localhost:8081/register', users);
  }

  UpdateUser(users: Users){
    return this.HttpClient.put(this.url, users);
  }

  RemoveUser(id : string){
    return this.HttpClient.delete(this.url+'/'+id);
  }

  LogIn(users : Users){
    return this.HttpClient.post('http://localhost:8081/login',users);
  }
}
