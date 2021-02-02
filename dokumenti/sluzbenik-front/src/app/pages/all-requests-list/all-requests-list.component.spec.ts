import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllRequestsListComponent } from './all-requests-list.component';

describe('AllRequestsListComponent', () => {
  let component: AllRequestsListComponent;
  let fixture: ComponentFixture<AllRequestsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllRequestsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllRequestsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
