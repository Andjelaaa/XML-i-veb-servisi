import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllDecisionListComponent } from './all-decision-list.component';

describe('AllDecisionListComponent', () => {
  let component: AllDecisionListComponent;
  let fixture: ComponentFixture<AllDecisionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllDecisionListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllDecisionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
