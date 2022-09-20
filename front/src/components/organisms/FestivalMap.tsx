import React from 'react';
import KakaoMap from '../atoms/KakaoMap';
import tw from 'twin.macro';
import styled from 'styled-components';
import Button from '../atoms/Button';
import Text from '../atoms/Text';

import { Restaurant } from '../../mocks/handlers/festival_recomm_dist';

interface PropTypes {
  coord: {
    lat: number;
    lng: number;
  };
  restaurantData?: Restaurant[] | undefined;
  clickHandler?: React.MouseEventHandler<HTMLButtonElement>;
}

// 맛집 추천 position 설정용 STMP
const PositionButton = styled.button`
  ${tw`absolute bottom-2 right-2 z-10`}
`;

/**
 *
 * @description
 * 맛집 추천 버튼과 카카오맵 컴포넌트가
 * 혼합된 컴포넌트
 * @author bell
 */

const FestivalMap = ({ coord, clickHandler, restaurantData }: PropTypes) => {
  return (
    <>
      <PositionButton>
        <Button isText={true} clickHandler={clickHandler}>
          <Text message={'맛집 추천'} />
        </Button>
      </PositionButton>
      <KakaoMap restaurantData={restaurantData} coord={coord} />
    </>
  );
};

export default FestivalMap;
