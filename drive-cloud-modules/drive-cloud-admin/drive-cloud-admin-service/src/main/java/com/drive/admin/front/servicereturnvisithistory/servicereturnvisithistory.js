import request from '@/utils/request'


// 分页查询客服回访记录列表
export function listPageServiceReturnVisitHistory(query) {
  return request({
    url: '/admin/serviceReturnVisitHistory/pageList',
    method: 'get',
    params: query
  })
}


// 分页查询客服回访记录列表
export function listServiceReturnVisitHistory(query) {
  return request({
    url: '/admin/serviceReturnVisitHistory/findList',
    method: 'get',
    params: query
  })
}

// 查询客服回访记录详细
export function getServiceReturnVisitHistory(id) {
  return request({
    url: '/admin/serviceReturnVisitHistory/' + id,
    method: 'get'
  })
}

// 新增客服回访记录
export function addServiceReturnVisitHistory(data) {
  return request({
    url: '/admin/serviceReturnVisitHistory',
    method: 'post',
    data: data
  })
}

// 修改客服回访记录
export function updateServiceReturnVisitHistory(data) {
  return request({
    url: '/admin/serviceReturnVisitHistory',
    method: 'put',
    data: data
  })
}

// 删除客服回访记录
export function delServiceReturnVisitHistory(id) {
  return request({
    url: '/admin/serviceReturnVisitHistory/' + id,
    method: 'delete'
  })
}


// 状态启用/停用客服回访记录
export function changeStatus(data) {
  return request({
    url: '/admin/serviceReturnVisitHistory/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出客服回访记录
export function exportServiceReturnVisitHistory(query) {
  return request({
    url: '/admin/serviceReturnVisitHistory/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}