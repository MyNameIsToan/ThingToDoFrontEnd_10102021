import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ShareDataService {
SharedID : string;
constructor() {
  this.SharedID = '';
}

}
