import request from '@/utils/request'


// 分页查询学车视频表列表
export function listPageDrivingVideo(query) {
  return request({
    url: '/basics/drivingVideo/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询学车视频表列表
export function listDrivingVideo(query) {
  return request({
    url: '/basics/drivingVideo/findList',
    method: 'post',
    params: query
  })
}

// 查询学车视频表详细
export function getDrivingVideo(id) {
  return request({
    url: '/basics/drivingVideo/' + id,
    method: 'get'
  })
}

// 新增学车视频表
export function addDrivingVideo(data) {
  return request({
    url: '/basics/drivingVideo',
    method: 'post',
    data: data
  })
}

// 修改学车视频表
export function updateDrivingVideo(data) {
  return request({
    url: '/basics/drivingVideo',
    method: 'put',
    data: data
  })
}

// 删除学车视频表
export function delDrivingVideo(id) {
  return request({
    url: '/basics/drivingVideo/' + id,
    method: 'delete'
  })
}


// 状态启用/停用学车视频表
export function changeStatus(data) {
  return request({
    url: '/basics/drivingVideo/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出学车视频表
export function exportDrivingVideo(query) {
  return request({
    url: '/basics/drivingVideo/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}