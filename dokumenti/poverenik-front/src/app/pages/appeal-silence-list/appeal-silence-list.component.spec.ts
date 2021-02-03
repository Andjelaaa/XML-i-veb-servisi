import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppealSilenceListComponent } from './appeal-silence-list.component';

describe('AppealSilenceListComponent', () => {
  let component: AppealSilenceListComponent;
  let fixture: ComponentFixture<AppealSilenceListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppealSilenceListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppealSilenceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
