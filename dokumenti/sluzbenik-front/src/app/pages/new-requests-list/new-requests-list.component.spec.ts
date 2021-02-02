import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRequestsListComponent } from './new-requests-list.component';

describe('NewRequestsListComponent', () => {
  let component: NewRequestsListComponent;
  let fixture: ComponentFixture<NewRequestsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewRequestsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewRequestsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
