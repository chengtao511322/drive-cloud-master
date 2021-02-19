import request from '@/utils/request'

                  
// 分页查询教练授课区域表列表
export function listPageCoachGiveArea(query) {
  return request({
    url: '/admin/coachGiveArea/pageList',
    method: 'get',
    params: query
  })
}


// 分页查询教练授课区域表列表
export function listCoachGiveArea(query) {
  return request({
    url: '/admin/coachGiveArea/findList',
    method: 'get',
    params: query
  })
}

// 查询教练授课区域表详细
export function getCoachGiveArea(id) {
  return request({
    url: '/admin/coachGiveArea/' + id,
    method: 'get'
  })
}

// 新增教练授课区域表
export function addCoachGiveArea(data) {
  return request({
    url: '/admin/coachGiveArea',
    method: 'post',
    data: data
  })
}

// 修改教练授课区域表
export function updateCoachGiveArea(data) {
  return request({
    url: '/admin/coachGiveArea',
    method: 'put',
    data: data
  })
}

// 删除教练授课区域表
export function delCoachGiveArea(id) {
  return request({
    url: '/admin/coachGiveArea/' + id,
    method: 'delete'
  })
}


// 状态启用/停用教练授课区域表
export function changeStatus(data) {
  return request({
    url: '/admin/coachGiveArea/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出教练授课区域表
export function exportCoachGiveArea(query) {
  return request({
    url: '/admin/coachGiveArea/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}