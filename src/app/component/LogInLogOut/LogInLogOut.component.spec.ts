/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { LogInLogOutComponent } from './LogInLogOut.component';

describe('LogInLogOutComponent', () => {
  let component: LogInLogOutComponent;
  let fixture: ComponentFixture<LogInLogOutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogInLogOutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogInLogOutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
