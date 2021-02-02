import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppealSilencePageComponent } from './appeal-silence-page.component';

describe('AppealSilencePageComponent', () => {
  let component: AppealSilencePageComponent;
  let fixture: ComponentFixture<AppealSilencePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppealSilencePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppealSilencePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
