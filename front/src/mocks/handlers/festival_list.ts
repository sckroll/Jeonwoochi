import { rest } from 'msw';

export interface MapData {
  festivalId: number;
  name: string;
  startDate: Date;
  finishDate: Date;
  contents: string;
  locate: string;
  // image: string;
  way: boolean;
  festivalTypeId: number;
  // x: number;
  // y: number;
  lat: number;
  lng: number;
}

/**
 * @description
 * mock-server : map
 *
 * @author bell
 */
const mapData: MapData[] = [
  {
    festivalId: 0,
    name: 'Sample festival',
    startDate: new Date('2022-08-22'),
    finishDate: new Date('2022-10-07'),
    contents: 'SSAFY 7기 특화 프로젝트 진행 중...',
    locate: '서울특별시 송파구 백제고분로44길 13-25',
    // image :
    way: false,
    festivalTypeId: 1,
    // x:
    // y:
    lat: 127.11313422641568,
    lng: 37.50708968446814,
  },
  {
    festivalId: 1,
    name: '김제지평선축제',
    startDate: new Date('2022-10-24'),
    finishDate: new Date('2022-10-31'),
    contents:
      '전국 축제 1등! 김제 지평선 축제!!! 하늘과 땅이 만나는 곳 지평선으로 놀러오세요',
    locate: '전라북도 김제시 화동길 37 아가방 김제점',
    // image :
    way: false,
    festivalTypeId: 1,
    // x:
    // y:
    lat: 126.89094137623249,
    lng: 35.799173454870235,
  },
  {
    festivalId: 2,
    name: '전주국제영화제',
    startDate: new Date('2022-11-31'),
    finishDate: new Date('2022-12-24'),
    contents: '맛과 영화의 도시 전주!! 전주국제영화제로 오세요!',
    locate: '전북 전주시 덕진구 덕진동1가',
    // image :
    way: false,
    festivalTypeId: 2,
    // x:
    // y:
    lat: 127.126143385886,
    lng: 35.8489873847612,
  },
];

export default rest.get('/festival-service/list', (req, res, ctx) => {
  return res(ctx.status(200), ctx.json(mapData));
});
