package com.tothenew

import com.tothenew.enums.Seriousness
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll


@TestFor(Subscription)
class SubscriptionSpec extends Specification {

    def "check topic uniqueness"() {
        given:
        Topic topic = new Topic();
        User user = new User();
        Seriousness seriousness = Seriousness.VERY_SERIOUS;
        Subscription subscriptionObj = new Subscription(topic: topic, user: user, seriousness: seriousness);

        when:
        subscriptionObj.save();

        then:
        Subscription.count() == 1;

        when:
        subscriptionObj = new Subscription(topic: topic, user: user, seriousness: seriousness);
        subscriptionObj.save();

        then:
        Subscription.count() == 1;
        subscriptionObj.errors.allErrors.size() == 1;
        subscriptionObj.errors.getFieldErrorCount('user') == 1;
    }


    def "check nullability of fields"() {
        given:
        Subscription subscriptionObj = new Subscription(topic: topic, user: user, seriousness: seriousness);

        when:
        boolean result = subscriptionObj.validate();

        then:
        result == expected

        where:

        sno | topic       | user       | seriousness              | expected
        1   | new Topic() | new User() | Seriousness.VERY_SERIOUS | true
        2   | null        | new User() | Seriousness.VERY_SERIOUS | false
        3   | new Topic() | null       | Seriousness.VERY_SERIOUS | false
        4   | new Topic() | new User() | null                     | false
    }

        @Unroll("Sno------------ #sno")
    def "check Visibility enum convertToEnum method"() {
        when:
        Seriousness result = Seriousness.convertToEnum(str)
        then:
        result == expected
        where:
        sno | str            | expected
        1   | "SERIOUS"      | Seriousness.SERIOUS
        2   | "VERY_SERIOUS" | Seriousness.VERY_SERIOUS
        3   | "CASUAL"       | Seriousness.CASUAL
        4   | "Serious"      | Seriousness.SERIOUS
        5   | "Very_serious" | Seriousness.VERY_SERIOUS
        6   | "Casual"       | Seriousness.CASUAL
        7   | "Private"      | null
        8   | "vikas "       | null
        9   | " "            | null
        10  | null           | null


    }
}
