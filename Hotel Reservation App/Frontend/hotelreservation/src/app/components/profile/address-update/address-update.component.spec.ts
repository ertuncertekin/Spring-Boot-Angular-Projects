import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddressUpdateComponent } from './address-update.component';

describe('AddressUpdateComponent', () => {
  let component: AddressUpdateComponent;
  let fixture: ComponentFixture<AddressUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddressUpdateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddressUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
