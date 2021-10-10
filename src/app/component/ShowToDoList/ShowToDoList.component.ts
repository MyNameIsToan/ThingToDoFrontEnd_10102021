import { ShareDataService } from './../../c_service/ShareData.service';
import { NoteService } from './../../c_service/Note.service';
import { Component, OnInit } from '@angular/core';
import { Notes } from 'src/app/common/Notes';
import {Router} from '@angular/router';

@Component({
  selector: 'app-ShowToDoList',
  templateUrl: './ShowToDoList.component.html',
  styleUrls: ['./ShowToDoList.component.css']
})
export class ShowToDoListComponent implements OnInit {
  Condition = "0";
  IsHidden = true;
  ListOfNote !: Notes[];
  ID!: bigint;
  constructor(private NoteService : NoteService, private router : Router, private ShareDataService: ShareDataService ) { }

  ngOnInit() {
    this.GetAll();
  }

  GetAll(){
    this.NoteService.GetAll().subscribe(data=>{
      this.ListOfNote = data as Notes[];
    });
  }

  RemoveNote(NoteID : bigint){
    this.ID = NoteID;
    this.IsHidden = false;
  }

  EditNote(NoteID: bigint){
    this.ShareDataService.SharedID = NoteID.toString();
    this.router.navigate(['/UpdateNote/' + NoteID]);
  }

  Cancel(){
    this.IsHidden = true;
  }

  Remove(){
    this.NoteService.RemoveNote(this.ID).subscribe();
    this.IsHidden = true;
    window.location.reload();
  }
}
