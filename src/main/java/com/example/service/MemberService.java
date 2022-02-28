package com.example.service;

import com.example.domain.Member;
import com.example.domain.MemberID;
import com.example.domain.MembershipID;
import com.example.domain.UserID;
import com.example.dto.body.ChangeMemberStatusDTO;
import com.example.exception.NotFoundException;
import com.example.exception.UnknownStatusException;
import com.example.repository.MemberRepository;
import com.example.repository.MembershipRepository;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;

    public MemberService(MemberRepository memberRepository, MembershipRepository membershipRepository, UserRepository userRepository) {
        this.memberRepository = memberRepository;
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
    }

    public Member addNewMember(Long userId, Long membershipType) {
        if (!this.userRepository.existsById(new UserID(userId))) {
            throw new NotFoundException("User " + userId + " not found");
        }

        if (!this.membershipRepository.existsById(new MembershipID(membershipType))) {
            throw new NotFoundException("Unable to associate user with membership: membership not found");
        }

        Long latestMemberID = this.memberRepository.count() + 1;

        MemberID memberId = new MemberID(latestMemberID);

        Member member = new Member();
        member.setId(memberId);
        member.setUserId(userId);
        member.setMembershipDate(LocalDate.now());
        member.setMembershipType(membershipType);
        member.setMembershipStatus("A");

        return this.memberRepository.save(member);
    }

    public Member changeMemberStatus(ChangeMemberStatusDTO changeStatus) {
        String status = changeStatus.getStatus();

        if (!status.equals("A") && !status.equals("I"))
            throw new UnknownStatusException("Não existe status do tipo " + status);

        Optional<Member> optionalMember;

        if (changeStatus.getUserId() != null)
            optionalMember = this.memberRepository.findByUserId(changeStatus.getUserId());
        else
            optionalMember = this.memberRepository.findById(new MemberID(changeStatus.getMemberId()));


        if (optionalMember.isEmpty())
            throw new NotFoundException("Member not found");

        Member member = optionalMember.get();

        member.setMembershipStatus(changeStatus.getStatus());
        if (changeStatus.getStatus().equals("A")) {
            member.setMembershipDate(LocalDate.now());
        }

        return this.memberRepository.save(member);
    }
}
