/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author saddam.husein
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
    public Member findByUsername(String username);
}
