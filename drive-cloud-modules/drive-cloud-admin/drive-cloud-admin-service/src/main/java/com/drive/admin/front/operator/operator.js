import request from '@/utils/request'


// 分页查询运营商基础信息列表
export function listPageOperator(query) {
  return request({
    url: '/admin/operator/pageList',
    method: 'get',
    params: query
  })
}


// 分页查询运营商基础信息列表
export function listOperator(query) {
  return request({
    url: '/admin/operator/findList',
    method: 'get',
    params: query
  })
}

// 查询运营商基础信息详细
export function getOperator(id) {
  return request({
    url: '/admin/operator/' + id,
    method: 'get'
  })
}

// 新增运营商基础信息
export function addOperator(data) {
  return request({
    url: '/admin/operator',
    method: 'post',
    data: data
  })
}

// 修改运营商基础信息
export function updateOperator(data) {
  return request({
    url: '/admin/operator',
    method: 'put',
    data: data
  })
}

// 删除运营商基础信息
export function delOperator(id) {
  return request({
    url: '/admin/operator/' + id,
    method: 'delete'
  })
}


// 状态启用/停用运营商基础信息
export function changeStatus(data) {
  return request({
    url: '/admin/operator/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出运营商基础信息
export function exportOperator(query) {
  return request({
    url: '/admin/operator/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}