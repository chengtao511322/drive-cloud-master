import request from '@/utils/request'

                                                
// 分页查询平台训练场地表列表
export function listPageCoachingGrid(query) {
  return request({
    url: '/admin/coachingGrid/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询平台训练场地表列表
export function listCoachingGrid(query) {
  return request({
    url: '/admin/coachingGrid/findList',
    method: 'post',
    params: query
  })
}

// 查询平台训练场地表详细
export function getCoachingGrid(id) {
  return request({
    url: '/admin/coachingGrid/' + id,
    method: 'get'
  })
}

// 新增平台训练场地表
export function addCoachingGrid(data) {
  return request({
    url: '/admin/coachingGrid',
    method: 'post',
    data: data
  })
}

// 修改平台训练场地表
export function updateCoachingGrid(data) {
  return request({
    url: '/admin/coachingGrid',
    method: 'put',
    data: data
  })
}

// 删除平台训练场地表
export function delCoachingGrid(id) {
  return request({
    url: '/admin/coachingGrid/' + id,
    method: 'delete'
  })
}


// 状态启用/停用平台训练场地表
export function changeStatus(data) {
  return request({
    url: '/admin/coachingGrid/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出平台训练场地表
export function exportCoachingGrid(query) {
  return request({
    url: '/admin/coachingGrid/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}