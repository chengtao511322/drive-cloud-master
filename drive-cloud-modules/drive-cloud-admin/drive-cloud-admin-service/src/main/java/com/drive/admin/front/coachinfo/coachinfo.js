import request from '@/utils/request'


// 查询教练信息表列表
export function listPageCoachInfo(query) {
  return request({
    url: '/admin/coachInfo/pageList',
    method: 'get',
    params: query
  })
}

// 查询教练信息表详细
export function getCoachInfo(id) {
  return request({
    url: '/admin/coachInfo/' + id,
    method: 'get'
  })
}

// 新增教练信息表
export function addCoachInfo(data) {
  return request({
    url: '/admin/coachInfo',
    method: 'post',
    data: data
  })
}

// 修改教练信息表
export function updateCoachInfo(data) {
  return request({
    url: '/admin/coachInfo',
    method: 'put',
    data: data
  })
}

// 删除教练信息表
export function delCoachInfo(id) {
  return request({
    url: '/admin/coachInfo/' + id,
    method: 'delete'
  })
}

// 导出教练信息表
export function exportCoachInfo(query) {
  return request({
    url: '/admin/coachInfo/exportXls',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}