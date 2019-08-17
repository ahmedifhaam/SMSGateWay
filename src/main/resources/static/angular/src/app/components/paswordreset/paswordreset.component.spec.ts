import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaswordresetComponent } from './paswordreset.component';

describe('PaswordresetComponent', () => {
  let component: PaswordresetComponent;
  let fixture: ComponentFixture<PaswordresetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaswordresetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaswordresetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
