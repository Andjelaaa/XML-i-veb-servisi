import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppealDecisionService } from 'src/app/services/appeal-decision-service/appeal-decision.service';

@Component({
  selector: 'app-appeal-decision-review',
  templateUrl: './appeal-decision-review.component.html',
  styleUrls: ['./appeal-decision-review.component.css']
})
export class AppealDecisionReviewComponent implements OnInit {

  @Input() id: any;
  public html = '';
  constructor(private appealDecisionService: AppealDecisionService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getAppealDecision();
  }

  getAppealDecision(): void {
    this.appealDecisionService.toHtml(this.id).subscribe(
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
