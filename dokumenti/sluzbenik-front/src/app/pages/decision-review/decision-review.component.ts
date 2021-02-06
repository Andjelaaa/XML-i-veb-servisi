import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DecisionService } from 'src/app/services/decision-service/decision.service';

@Component({
  selector: 'app-decision-review',
  templateUrl: './decision-review.component.html',
  styleUrls: ['./decision-review.component.css']
})
export class DecisionReviewComponent implements OnInit {

  @Input() id: any;
  public html = '';
  constructor(private decisionService: DecisionService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getDecision();
  }

  getDecision(): void {
    this.decisionService.toHtml(this.id).subscribe(
      (response => {
        if (!response) {
          alert('File not exist or is empty');
        }
        this.html = response;
      }),
      (error => {
        console.log(JSON.stringify(error));
        alert(error.error.message);
      })
    );
    }
}
