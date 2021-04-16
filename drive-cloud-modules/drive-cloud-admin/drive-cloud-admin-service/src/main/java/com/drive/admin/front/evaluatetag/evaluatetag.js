import request from '@/utils/request'


// 分页查询评价标签表列表
export function listPageEvaluateTag(query) {
  return request({
    url: '/admin/evaluateTag/pageList',
    method: 'get',
    params: query
  })
}


// 分页查询评价标签表列表
export function listEvaluateTag(query) {
  return request({
    url: '/admin/evaluateTag/findList',
    method: 'get',
    params: query
  })
}

// 查询评价标签表详细
export function getEvaluateTag(id) {
  return request({
    url: '/admin/evaluateTag/' + id,
    method: 'get'
  })
}

// 新增评价标签表
export function addEvaluateTag(data) {
  return request({
    url: '/admin/evaluateTag',
    method: 'post',
    data: data
  })
}

// 修改评价标签表
export function updateEvaluateTag(data) {
  return request({
    url: '/admin/evaluateTag',
    method: 'put',
    data: data
  })
}

// 删除评价标签表
export function delEvaluateTag(id) {
  return request({
    url: '/admin/evaluateTag/' + id,
    method: 'delete'
  })
}


// 状态启用/停用评价标签表
export function changeStatus(data) {
  return request({
    url: '/admin/evaluateTag/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出评价标签表
export function exportEvaluateTag(query) {
  return request({
    url: '/admin/evaluateTag/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}