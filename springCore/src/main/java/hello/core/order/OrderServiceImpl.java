package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor    //final이 붙으면 필수값, final 붙은거를 가지고 생성자 만들어줌
public class OrderServiceImpl implements OrderService {


    /*@Autowired -> 필드 주입은 외부에서 변경이 불가능해 테스트가 힘들다,
    그러니 사용하지 말자!
     */
    // 생성자 주입에서만 final 사용 가능
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired  //생성자가 1개일때는 @Autowired 안붙여도 의존관계 주입됨
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
