import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Empreendimento } from './empreendimento';

describe('Empreendimento', () => {
  let component: Empreendimento;
  let fixture: ComponentFixture<Empreendimento>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Empreendimento]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Empreendimento);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
