import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllAppealDecisionListComponent } from './all-appeal-decision-list.component';

describe('AllAppealDecisionListComponent', () => {
  let component: AllAppealDecisionListComponent;
  let fixture: ComponentFixture<AllAppealDecisionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllAppealDecisionListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllAppealDecisionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
