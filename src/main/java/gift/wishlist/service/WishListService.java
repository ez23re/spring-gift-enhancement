package gift.wishlist.service;

import gift.member.repository.MemberRepository;
import gift.member.service.MemberService;
import gift.product.model.Product;
import gift.product.repository.ProductRepository;
import gift.wishlist.model.WishList;
import gift.wishlist.repository.WishListRepository;
import gift.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final MemberService memberService;

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public WishListService(WishListRepository wishListRepository, MemberRepository memberRepository, MemberService memberService, ProductRepository productRepository) {
        this.wishListRepository = wishListRepository;
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    // id로 위시리스트 찾기 (단일 객체 반환)
    public WishList findByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow();
        return wishListRepository.findByMember(member);
    }


    /** wishListRepository의 findByMemberId 메소드를 호출하여
     데이터베이스에서 페이지네이션된 결과를 가져옴. **/
    public Page<WishList> findByMemberId(Long memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow();
        return wishListRepository.findByMember(member, pageable);
    }

    // 위시리스트에 상품 추가
    @Transactional
    public void addProductToWishList(Long memberId, Product product) {
        Member member = memberService.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("옳지않은 회원 id 입니다."));
        WishList wishList = new WishList(member, product);
        wishListRepository.save(wishList);
    }

    // 위시리스트에 상품 삭제
    @Transactional
    public void removeProductFromWishList(Long memberId, Long productId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow();
        Product product = productRepository.findById(productId)
                .orElseThrow();
        Optional<WishList> wishListOptional = wishListRepository.findByMemberAndProduct(member, product);
        wishListOptional.ifPresent(wishListRepository::delete);
    }
}