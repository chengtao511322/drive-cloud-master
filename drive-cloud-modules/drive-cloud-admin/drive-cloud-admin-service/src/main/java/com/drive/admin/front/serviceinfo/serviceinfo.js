import request from '@/utils/request'

                                            
// 分页查询客服人员信息表列表
export function listPageServiceInfo(query) {
  return request({
    url: '/admin/serviceInfo/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询客服人员信息表列表
export function listServiceInfo(query) {
  return request({
    url: '/admin/serviceInfo/findList',
    method: 'post',
    params: query
  })
}

// 查询客服人员信息表详细
export function getServiceInfo(id) {
  return request({
    url: '/admin/serviceInfo/' + id,
    method: 'get'
  })
}

// 新增客服人员信息表
export function addServiceInfo(data) {
  return request({
    url: '/admin/serviceInfo',
    method: 'post',
    data: data
  })
}

// 修改客服人员信息表
export function updateServiceInfo(data) {
  return request({
    url: '/admin/serviceInfo',
    method: 'put',
    data: data
  })
}

// 删除客服人员信息表
export function delServiceInfo(id) {
  return request({
    url: '/admin/serviceInfo/' + id,
    method: 'delete'
  })
}


// 状态启用/停用客服人员信息表
export function changeStatus(data) {
  return request({
    url: '/admin/serviceInfo/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出客服人员信息表
export function exportServiceInfo(query) {
  return request({
    url: '/admin/serviceInfo/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}