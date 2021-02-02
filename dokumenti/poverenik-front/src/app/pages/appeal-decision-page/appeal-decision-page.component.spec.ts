import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppealDecisionPageComponent } from './appeal-decision-page.component';

describe('AppealDecisionPageComponent', () => {
  let component: AppealDecisionPageComponent;
  let fixture: ComponentFixture<AppealDecisionPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppealDecisionPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppealDecisionPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
