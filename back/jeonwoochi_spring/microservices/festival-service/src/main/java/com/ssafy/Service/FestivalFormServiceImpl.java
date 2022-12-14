package com.ssafy.Service;

import com.ssafy.Domain.Entity.FestivalForm;
import com.ssafy.Domain.Repository.FestivalFormRepo;
import com.ssafy.Dto.FestivalFormCreateRequest;
import com.ssafy.Dto.FestivalFormResponse;
import com.ssafy.Dto.FestivalFormUpdateRequest;
import com.ssafy.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FestivalFormServiceImpl implements FestivalFormService{
    private final FestivalFormRepo festivalFormRepo;
    // 축제 요청 추가
    @Override
    @Transactional
    public void createFestivalForm(FestivalFormCreateRequest request, Long userId, String imgUrl) {
            FestivalForm festivalForm = FestivalForm.create(request, userId, imgUrl);
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
    public Page<FestivalFormResponse> findFestivalFormListAll(Pageable pageable) {
        Page<FestivalFormResponse> festivalForms = festivalFormRepo.findAllByOrderByIdDesc(pageable)
                .map(FestivalFormResponse::response);

        return festivalForms;
    }
    // 내가 요청한 축제 리스트 조회
    @Override
    @Transactional
    public Page<FestivalFormResponse> findFestivalFormListByMe(Long userId, Pageable pageable) {
        Page<FestivalFormResponse> festivalForms = festivalFormRepo.findByUserIdOrderByIdDesc(userId, pageable)
                .map(FestivalFormResponse::response);
        return festivalForms;
    }

    // 축제 요청 수정
    @Override
    @Transactional
    public FestivalFormResponse updateFestivalForm(FestivalFormUpdateRequest request) {
        FestivalForm festivalForm = festivalFormRepo.findById(request.getId())
                .orElseThrow(()-> new NotFoundException(FESTIVAL_FORM_NOT_FOUND));
        festivalForm.update(request);
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
