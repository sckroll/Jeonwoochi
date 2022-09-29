package com.ssafy.Service;

import com.ssafy.Domain.Entity.Festival;
import com.ssafy.Domain.Entity.FestivalForm;
import com.ssafy.Domain.Entity.FestivalType;
import com.ssafy.Domain.Repository.FestivalFormRepo;
import com.ssafy.Domain.Repository.FestivalTypeRepo;
import com.ssafy.Dto.FestivalFormCreateRequest;
import com.ssafy.Dto.FestivalFormResponse;
import com.ssafy.Dto.FestivalFormUpdateRequest;
import com.ssafy.Dto.FestivalUpdateRequest;
import com.ssafy.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FestivalFormServiceImpl implements FestivalFormService{
    private final FestivalFormRepo festivalFormRepo;
    private final FestivalTypeRepo festivalTypeRepo;
    // 축제 요청 추가
    @Override
    @Transactional
    public void createFestivalForm(FestivalFormCreateRequest request, Long userId, String imgUrl) {
            FestivalType festivalType = festivalTypeRepo.findById(request.getFestivalTypeId())
                    .orElseThrow(()-> new NotFoundException(FESTIVAL_TYPE_NOT_FOUND));
            FestivalForm festivalForm = FestivalForm.create(request, festivalType, userId, imgUrl);
            festivalFormRepo.save(festivalForm);
    }
    // 축제 요청 상세 보기
    @Override
    @Transactional
    public FestivalFormResponse findFestivalFormDetail(Long festivalFormId) {
        FestivalForm festivalForm = festivalFormRepo.findById(festivalFormId)
                .orElseThrow(()->new NotFoundException(FESTIVAL_FORM_NOT_FOUND));
        return FestivalFormResponse.response(festivalForm);
    }
    // 축제 요청 전체 리스트 조회
    @Override
    @Transactional
    public List<FestivalFormResponse> findFestivalFormListAll() {
        List<FestivalFormResponse> festivalForms = festivalFormRepo.findAll().stream()
                .map(FestivalFormResponse::response)
                .collect(Collectors.toList());
        return festivalForms;
    }
    // 내가 요청한 축제 리스트 조회
    @Override
    @Transactional
    public List<FestivalFormResponse> findFestivalFormListByMe(Long userId) {
        List<FestivalFormResponse> festivalForms = festivalFormRepo.findByUserId(userId).stream()
                .map(FestivalFormResponse::response)
                .collect(Collectors.toList());
        return festivalForms;
    }
    // 축제 요청 수정
    @Override
    @Transactional
    public FestivalFormResponse updateFestivalForm(FestivalFormUpdateRequest request) {
        FestivalType festivalType = festivalTypeRepo.findById(request.getFestivalTypeId())
                .orElseThrow(()-> new NotFoundException(FESTIVAL_TYPE_NOT_FOUND));
        FestivalForm festivalForm = festivalFormRepo.findById(request.getId())
                .orElseThrow(()-> new NotFoundException(FESTIVAL_FORM_NOT_FOUND));
        festivalForm.update(request, festivalType);
        return FestivalFormResponse.response(festivalForm);
    }
    // 축제 요청 삭제
    @Override
    @Transactional
    public void deleteFestivalForm(Long festivalFormId) {
        FestivalForm festivalForm = festivalFormRepo.findById(festivalFormId)
                .orElseThrow(()->new NotFoundException(FESTIVAL_NOT_FOUND));
        festivalFormRepo.delete(festivalForm);
    }
}