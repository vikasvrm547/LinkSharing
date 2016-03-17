package com.tothenew.vo

class RatingInfoVO {
    Long totalVotes
    Long averageScore
    Long totalScore

    String toString() {
        return "Total votes: $totalVotes, Average score: $averageScore, Total score: $totalScore"
    }
}
