import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppealDecisionListComponent } from './appeal-decision-list.component';

describe('AppealDecisionListComponent', () => {
  let component: AppealDecisionListComponent;
  let fixture: ComponentFixture<AppealDecisionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppealDecisionListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppealDecisionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
