import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewAppealSilenceListComponent } from './new-appeal-silence-list.component';

describe('NewAppealSilenceListComponent', () => {
  let component: NewAppealSilenceListComponent;
  let fixture: ComponentFixture<NewAppealSilenceListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewAppealSilenceListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewAppealSilenceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
