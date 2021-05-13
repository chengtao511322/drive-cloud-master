import request from '@/utils/request'

                          
// 分页查询运营商加盟申请列表
export function listPageOperatorRecruit(query) {
  return request({
    url: '/admin/operatorRecruit/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询运营商加盟申请列表
export function listOperatorRecruit(query) {
  return request({
    url: '/admin/operatorRecruit/findList',
    method: 'post',
    data: query
  })
}

// 查询运营商加盟申请详细
export function getOperatorRecruit(id) {
  return request({
    url: '/admin/operatorRecruit/' + id,
    method: 'get'
  })
}

// 新增运营商加盟申请
export function addOperatorRecruit(data) {
  return request({
    url: '/admin/operatorRecruit',
    method: 'post',
    data: data
  })
}

// 修改运营商加盟申请
export function updateOperatorRecruit(data) {
  return request({
    url: '/admin/operatorRecruit',
    method: 'put',
    data: data
  })
}

// 删除运营商加盟申请
export function delOperatorRecruit(id) {
  return request({
    url: '/admin/operatorRecruit/' + id,
    method: 'delete'
  })
}


// 状态启用/停用运营商加盟申请
export function changeStatus(data) {
  return request({
    url: '/admin/operatorRecruit/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出运营商加盟申请
export function exportOperatorRecruit(query) {
  return request({
    url: '/admin/operatorRecruit/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}