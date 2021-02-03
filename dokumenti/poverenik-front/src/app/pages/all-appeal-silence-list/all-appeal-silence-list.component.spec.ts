import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllAppealSilenceListComponent } from './all-appeal-silence-list.component';

describe('AllAppealSilenceListComponent', () => {
  let component: AllAppealSilenceListComponent;
  let fixture: ComponentFixture<AllAppealSilenceListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllAppealSilenceListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllAppealSilenceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
