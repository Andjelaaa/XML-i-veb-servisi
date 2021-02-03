import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewAppealDecisionListComponent } from './new-appeal-decision-list.component';

describe('NewAppealDecisionListComponent', () => {
  let component: NewAppealDecisionListComponent;
  let fixture: ComponentFixture<NewAppealDecisionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewAppealDecisionListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewAppealDecisionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
