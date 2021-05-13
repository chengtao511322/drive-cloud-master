import request from '@/utils/request'


// 分页查询运营商代理区域列表
export function listPageOperatorArea(query) {
  return request({
    url: '/basics/operatorArea/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询运营商代理区域列表
export function listOperatorArea(query) {
  return request({
    url: '/basics/operatorArea/findList',
    method: 'post',
    data: query
  })
}

// 查询运营商代理区域详细
export function getOperatorArea(id) {
  return request({
    url: '/basics/operatorArea/' + id,
    method: 'get'
  })
}

// 新增运营商代理区域
export function addOperatorArea(data) {
  return request({
    url: '/basics/operatorArea',
    method: 'post',
    data: data
  })
}

// 修改运营商代理区域
export function updateOperatorArea(data) {
  return request({
    url: '/basics/operatorArea',
    method: 'put',
    data: data
  })
}

// 删除运营商代理区域
export function delOperatorArea(id) {
  return request({
    url: '/basics/operatorArea/' + id,
    method: 'delete'
  })
}


// 状态启用/停用运营商代理区域
export function changeStatus(data) {
  return request({
    url: '/basics/operatorArea/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出运营商代理区域
export function exportOperatorArea(query) {
  return request({
    url: '/basics/operatorArea/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}